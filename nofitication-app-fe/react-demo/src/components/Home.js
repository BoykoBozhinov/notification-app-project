import { useNavigate } from "react-router-dom"
import Background from '../images/connect.jpg'

const SelectAction = () => {

    const navigate = useNavigate()

    function sendSmsMessage() {

        navigate('/sendSms')
    }

    function sendEmail() {
        navigate('/sendEmail')
    }

    return (

        <div className="d-flex align-items-center justify-content-center flex-column" style={
            {
                height: '100vh',
                backgroundImage: `url(${Background})`,
                backgroundRepeat: 'no-repeat',
                backgroundSize: 'cover'
            }}>
            <button className="btn btn-primary mb-5 btn-lg" style={{ padding: 20 }} onClick={sendSmsMessage}>Send SMS</button>
            <button className="btn btn-primary btn-lg" style={{ padding: 20 }} onClick={sendEmail}>Send Email</button>

        </div>
    )
}

export default SelectAction