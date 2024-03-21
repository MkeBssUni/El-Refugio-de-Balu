# Establece la imagen base de Node.js
FROM node:20-alpine3.18 as builder
# Establece el directorio de trabajo para la construcción de Vue
WORKDIR /app/frontend
# Copia solo los archivos necesarios para instalar las dependencias
COPY package.json package-lock.json ./
# Instala las dependencias de Vue.js
RUN npm install
# Copia el resto del código fuente de Vue
COPY . .
# Construye la aplicación Vue.js
RUN npm run build
# Utiliza una imagen más pequeña para servir la aplicación Vue.js
FROM nginx:1.25.3-alpine-perl
# Copia los archivos construidos de Vue a la carpeta de Nginx
COPY --from=builder /app/frontend/dist /usr/share/nginx/html
# Exponer el puerto 80
EXPOSE 80
# El contenedor de Nginx se inicia automáticamente, no es necesario un CMD
CMD ["nginx","-g","daemon off;"]