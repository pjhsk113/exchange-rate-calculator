<template>
  <div>
    <headers msg="환율 계산"/>
    <exchange-calculator/>
  </div>

</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator'
import Headers from '@/components/Headers.vue'
import ExchangeCalculator from '@/components/ExchangeCalculator.vue' // @ is an alias to /src

@Component({
  components: {
    ExchangeCalculator,
    Headers
  }
})
export default class Home extends Vue {}
</script>
