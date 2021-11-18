import {IExchangeRateForm} from '@/type/types'
import {CurrencyExchangeCodes} from '@/type/enum'

export const defaultExchangeRateForm: IExchangeRateForm = {
  recipientCountry: CurrencyExchangeCodes.한국,
  remittanceAmount: 100
}
