
export const exchangeRateFormat = (value: number) => {
  const exchangeRate = value.toFixed(2)
  return amountUnitDisplay(exchangeRate)
}

export const amountUnitDisplay = (amount: string) => {
  const formattedAmount = amount.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ',')
  return formattedAmount
}
