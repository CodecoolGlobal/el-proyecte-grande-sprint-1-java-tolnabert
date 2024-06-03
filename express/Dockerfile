FROM node:slim
# ENV NODE_ENV deployment
# check later the env syntax
WORKDIR /express
COPY package*.json ./
RUN npm install
COPY . .
CMD ["node", "index.js"]
EXPOSE 5000