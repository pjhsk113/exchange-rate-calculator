import request from '@/util/request'
import {IExchangeRateForm} from '@/type/types'

/**
 * 환율 계산 백엔드 API 호출
 */

export const getExchangeRate = () =>
  request({
    url: '/exchange-rate/info',
    method: 'get'
  })

export const getExchangeAmount = (params: IExchangeRateForm) =>
  request({
    url: '/exchange-rate/amount',
    method: 'get',
    params
  })
