<template>
  <el-row :gutter="20">
    <el-col :span="8" :offset="10">
      <div class="grid-content bg-purple">
        <el-form
          ref="exchangeRateRef"
          :model="exchangeRateForm"
          :rules="formRules"
          label-width="70px"
          label-position="left">
          <el-form-item
            label="송금국가:">
            {{ "미국(USD)" }}
          </el-form-item>
          <el-form-item
            label="수취국가:"
            prop="region">
            <el-select
              v-model="exchangeRateForm.recipientCountry"
              @change="selectedCountry">
              <el-option
                v-for="(code, name) in currencyExchangeCodes"
                :key="code"
                :label="name"
                :value="code">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="환율:">
            {{ exchangeRate }} {{ currencyCode + "/USD"}}
          </el-form-item>
          <el-form-item
            prop="remittanceAmount"
            label="송금액:">
            <el-input-number
              v-model="exchangeRateForm.remittanceAmount"
              :controls="false">
              USD
            </el-input-number>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              size="medium"
              @click="getExchangeAmount">
              submit
            </el-button>
          </el-form-item>
        </el-form>
        <div
          v-if="resultDisplay"
          class="grid-content bg-purple"
          style="text-align-last: start;">
          <h3> 수취 금액은 {{ exchangeAmount }} {{ currencyCode }} 입니다.</h3>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator'
import {CurrencyCodes, CurrencyExchangeCodes} from '@/type/enum'
import {IExchangeRateForm} from '@/type/types'
import {defaultExchangeRateForm} from '@/type/type'
import {getExchangeAmount, getExchangeRate} from '@/api/exchangeRate'
import {exchangeRateFormat} from '@/util/formatter'
import {validate} from '@/util/validate'
import {Message} from 'element-ui'

@Component
export default class ExchangeCalculator extends Vue {
  private exchangeRateForm: IExchangeRateForm = defaultExchangeRateForm;
  private exchangeRate = '';
  private currencyCode = '';
  private exchangeAmount = '';
  private resultDisplay = false;

  mounted () {
    this.callExchangeRateApi()
  }

  get currencyExchangeCodes () {
    return CurrencyExchangeCodes
  }

  private selectedCountry () {
    this.callExchangeRateApi()
    this.resultDisplay = false
  }

  private getExchangeAmount () {
    Promise.all([
      validate(this.$refs.exchangeRateRef)
    ]).then(() => {
      getExchangeAmount(this.exchangeRateForm).then((response) => {
        const exchangeAmountData = response.data
        this.exchangeAmount = exchangeRateFormat(exchangeAmountData.remittanceAmount)
        this.resultDisplay = true
      })
    }).catch(() => {
      Message.warning('송금액이 올바르지 않습니다.')
    })
  }

  private callExchangeRateApi () {
    getExchangeRate().then((response) => {
      console.log(response)
      const exchangeRateData = response.data
      this.setExchangeRateInformation(exchangeRateData)
    })
  }

  private setExchangeRateInformation (ExchangeRateData: any) {
    const countryCode = this.exchangeRateForm.recipientCountry
    this.exchangeRate = exchangeRateFormat(ExchangeRateData[countryCode])
    this.currencyCode = CurrencyCodes[countryCode as keyof typeof CurrencyCodes]
  }

  private formRules = {
    remittanceAmount: [{ required: true, trigger: 'blur', validator: this.validateForm }]
  }

  private validateForm (rule: any, value: string, callback: Function) {
    const prop: string = rule.field
    const data: any = this.exchangeRateForm
    if (prop === 'remittanceAmount') {
      if (isNaN(Number(data.remittanceAmount))) {
        return callback(new Error('송금액이 바르지 않습니다.'))
      }

      if (data.remittanceAmount < 0) {
        return callback(new Error('송금액은 0 이상의 값만 입력할 수 있습니다.'))
      }

      if (data.remittanceAmount > 10000) {
        return callback(new Error('송금액은 10,000 이하의 값만 입력할 수 있습니다.'))
      }
    }
    return callback()
  }
}
</script>

<style scoped lang="scss">

.el-row-margin {
  margin-top: 20px;
}

.el-form-item {
  width: 350px;
  text-align: justify;
  font-weight: bold;
}
</style>
