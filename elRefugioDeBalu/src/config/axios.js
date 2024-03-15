import axios from "axios";
import { decrypt } from "../kernel/hashFunctions";


const SERVER_URL = "http://localhost:8080/api";

const instance = axios.create({
    baseURL:SERVER_URL,
    timeout:30_000
});

instance.interceptors.request.use(async (config)=>{
    const token = await decrypt(localStorage.getItem("token"));
    if(token){
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
})

export default instance;