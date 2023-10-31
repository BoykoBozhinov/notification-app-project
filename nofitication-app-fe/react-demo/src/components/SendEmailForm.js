import { useNavigate } from "react-router-dom"
import { useState } from "react"
import EmailCss from './Email.module.css'
import { sendEmail } from "../services/SendEmailService"

const SendEmail = () => {

  const [emailFrom, setEmailFrom] = useState('')
  const [emailTo, setEmailTo] = useState('')
  const [message, setMessage] = useState('')
  const [subject, setSubject] = useState('')

  const navigate = useNavigate()

  function sendEmailHandler(e) {
    e.preventDefault()

    const email = {emailFrom, emailTo, subject, message }

    sendEmail(email).then((response) => {
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
    <div className={EmailCss.emailBackground}>
      <div className="mask d-flex align-items-center h-100">
        <button className="btn btn-primary btn-lg ms-4" onClick={redirectHome}>Home</button>
        <div className="container h-100 mb-5 mt-5">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-9 col-lg-7 col-xl-6">
              <div className="card">
                <div className="card-body p-4">
                  <h2 className="text-uppercase text-center mb-3">Send Email</h2>
                  <form>
                    <div className="form-outline mb-3">
                      <input
                      type="text"
                      id="form3Example1cg"
                      className="form-control form-control-lg"
                      value={emailFrom}
                      onChange={(e) => setEmailFrom(e.target.value)}
                      />
                      <label className="form-label" for="form3Example1cg">Send Email From</label>
                    </div>

                    <div className="form-outline mb-4">
                      <input
                      type="email"
                      id="form3Example3cg"
                      className="form-control form-control-lg"
                      value={emailTo}
                      onChange={(e) => setEmailTo(e.target.value)}
                      />
                      <label className="form-label" for="form3Example3cg">Send Email To</label>
                    </div>

                    <div className="form-outline mb-4">
                      <input
                      type="email"
                      id="form3Example3cg"
                      className="form-control form-control-lg"
                      value={subject}
                      onChange={(e) => setSubject(e.target.value)}
                      />
                      <label className="form-label" for="form3Example3cg">Subject</label>
                    </div>

                    <div className="form-outline h-100">
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
                      <button type="submit"className="btn btn-primary btn-block btn-lg ps-4 pe-4 mt-3"
                          onClick={(e) => sendEmailHandler(e)}>
                            Send
                        </button>
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

export default SendEmail