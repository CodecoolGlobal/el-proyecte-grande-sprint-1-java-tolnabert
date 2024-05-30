FROM node:slim
ENV NODE_ENV development
WORKDIR /express
COPY package*.json ./
RUN npm install
COPY . .
CMD ["node", "index.js"]
EXPOSE 5000