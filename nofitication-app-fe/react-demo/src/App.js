import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import SendSmsForm from './components/SendSmsForm.js'
import Home from './components/Home'
import SendEmailForm from './components/SendEmailForm'
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Home />}></Route>
          <Route path='/sendSms' element={<SendSmsForm />}></Route>
          <Route path='/sendEmail' element={<SendEmailForm />}></Route>
        </Routes>

      </BrowserRouter>
    </>
  )
}

export default App;
