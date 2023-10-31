import axios from 'axios'

const BASE_URL_API_URL = 'http://localhost:8080/api/v1/sms';

export const sendSms = (message) => axios.post(BASE_URL_API_URL, message);