# Stage 1: Build Stage
FROM node:16 AS builder
WORKDIR /express
COPY package*.json ./
RUN npm install
COPY . .

# Stage 2: Runtime Environment
FROM node:slim
WORKDIR /express
COPY --from=builder /express ./
CMD ["node", "index.js"]
EXPOSE 5000