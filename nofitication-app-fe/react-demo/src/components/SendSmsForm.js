import React from "react"
import { useState } from "react"
import { sendSms } from "../services/SendSmsService"
import { useNavigate } from "react-router-dom"
import SmsCss from './Sms.module.css'

const SendSms = () => {

  const [message, setMessage] = useState('')
  const [phoneNumber, setPhoneNumber] = useState('')

  const navigate = useNavigate()

  function sendSmsHandler(e) {
    e.preventDefault()

    const messageText = { phoneNumber, message }

    sendSms(messageText).then((response) => {
      console.log(response.data)
      redirectHome()
    }).catch(error => {
      console.error(error);
    })
  }

  function redirectHome() {
    navigate('/')
  }

  return (
    <div className={SmsCss.smsForm}>
      <div className="mask d-flex align-items-center h-100 gradient-custom-3">
        <button className="btn btn-primary btn-lg m-3" onClick={redirectHome}>Home</button>
        <div className="container h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-9 col-lg-7 col-xl-6">
              <div className="card">
                <div className="card-body p-5">
                  <h2 className="text-uppercase text-center mb-5">Send SMS</h2>

                  <form>
                    <div className="form-outline mb-4">
                      <input
                        type="text"
                        id="form3Example1cg"
                        className="form-control form-control-lg"
                        value={phoneNumber}
                        onChange={(e) => setPhoneNumber(e.target.value)}
                      />
                      <label className="form-label" for="form3Example1cg">Enter phone number and country code</label>
                    </div>

                    <div className="form-outline mb-4">
                      <label className="mt-2 mb-2" htmlFor="message">Message</label>
                      <textarea
                        className="form-control"
                        id="message"
                        rows="5"
                        value={message}
                        onChange={(e) => setMessage(e.target.value)}>
                      </textarea>
                    </div>
                    <div className="d-flex justify-content-center">
                      <button type="submit" className="btn btn-primary btn-block btn-lg ps-4 pe-4 gradient-custom-4" onClick={(e) => sendSmsHandler(e)}>Send</button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default SendSms 