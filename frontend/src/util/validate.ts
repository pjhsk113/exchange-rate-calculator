
export const validateForm = function (exchangeRateForm: any) {
  return new Promise(function (resolve, reject) {
    exchangeRateForm.$refs.form.validate((valid: any) => {
      if (!valid) { reject(false) } else { resolve(true) }
    })
  })
}

export const validate = function (exchangeRateForm: any) {
  return new Promise(function (resolve, reject) {
    exchangeRateForm.validate((valid: any) => {
      if (!valid) { reject(false) } else { resolve(true) }
    })
  })
}
