import axios from "axios";

const BASE_URL_API = 'http://localhost:8080/api/v1/notification/send/email';

export const sendEmail = (email) => axios.post(BASE_URL_API, email);