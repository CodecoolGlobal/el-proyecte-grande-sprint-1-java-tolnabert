import React, { useState } from "react";
import FormRow from "../components/FormRow";
import { Link, useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();

  type FormDataType = {
    username: string;
    password: string;
  };

  const [formData, setFormData] = useState<FormDataType>({
    username: "",
    password: "",
  });

  const [message, setMessage] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      const response = await fetch("/api/clients/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        const data = await response.json();
        console.log("login", data);
        localStorage.setItem("jwtToken", data.jwt);
        localStorage.setItem("roles", data.roles);
        localStorage.setItem("username", data.username);
        navigate("/");
      } else {
        setMessage("Incorrect username or password");
      }
    } catch (error) {
      console.error("An error occurred:", error);
      setMessage("An error occurred while logging in");
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <FormRow
          type='text'
          name='username'
          labelText='Username: '
          value={formData.username}
          onChange={handleChange}
          required
        />
        <FormRow
          type='password'
          name='password'
          labelText='Password: '
          value={formData.password}
          onChange={handleChange}
          required
        />
        <button type='submit' disabled={loading}>
          {loading ? "Logging in..." : "Login"}
        </button>
        {message && <p>{message}</p>}
      </form>
      <p>
        Not a member yet? <Link to='/register'>register</Link>
      </p>
    </>
  );
}
export default Login;
