import { NavLink } from "react-router-dom";
import "../css/header.css";
import Logo from "./Logo";
import "./css/header.css";
import { useRef, useState } from "react";
import { fetchPatchPwChangeAuthParams } from "../utils/types";
import { fetchPatchPwChangeAuth } from "../fetchMethods";
import ChangePassword from "./ChangePassword";
import { PasswordChange } from "../utils/types";

function Header() {
  const [message, setMessage] = useState<string>("");
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const dialogRef = useRef<HTMLDialogElement>(null);
  const [formData, setFormData] = useState<PasswordChange>({
    oldPassword: "",
    newPassword: "",
    confirmationPassword: "",
  });
  const jwtToken = localStorage.getItem("jwtToken");
  const isLoggedIn = jwtToken !== null;

  const handleLogout = () => {
    localStorage.removeItem("jwtToken");
    localStorage.removeItem("roles");
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setMessage("");

    if (formData.oldPassword === formData.newPassword) {
      setMessage("Passwords should not match to old password!");
      setIsLoading(false);
      return;
    }

    if (formData.newPassword !== formData.confirmationPassword) {
      setMessage("Passwords should match for new password!");
      setIsLoading(false);
      return;
    }

    setIsLoading(true);
    try {
      const params: fetchPatchPwChangeAuthParams = {
        url: "/api/clients/user/change-password",
        jwtToken: jwtToken || "",
        newPassword: formData.newPassword,
      };

      const response = await fetchPatchPwChangeAuth(params);

      if (response.ok) {
        setMessage("Password changed");
      } else {
        const data = await response.json();
        setMessage(data.message || "Failed to update password");
      }
    } catch (error) {
      console.error("Error during password change:", error);
      setMessage(
        "An error occurred during password change. Please try again later."
      );
    } finally {
      setIsLoading(false);
    }
  };

  const openDialog = () => {
    if (dialogRef.current) {
      dialogRef.current.showModal();
    }
  };

  const closeDialog = () => {
    if (dialogRef.current) {
      dialogRef.current.close();
    }
  };

  return (
    <div className='header'>
      <Logo />
      {isLoggedIn ? (
        <div className='header-links'>
          <div className='dropdown'>
            <a className='nav-link-button'>My Profile</a>
            <div className='dropdown-content'>
              <a onClick={openDialog}>Change Password</a>
              <NavLink to={"/change-profile"}>Change Data</NavLink>
            </div>
          </div>
          <div>
            <NavLink
              className='nav-link-button'
              to={"/"}
              onClick={handleLogout}
            >
              Logout
            </NavLink>
          </div>
        </div>
      ) : (
        <>
          <NavLink className='nav-link-button' to={"/register"}>
            Register
          </NavLink>
          <NavLink className='nav-link-button' to={"/login"}>
            Login
          </NavLink>
        </>
      )}

      <ChangePassword
        dialogRef={dialogRef}
        formData={formData}
        isLoading={isLoading}
        message={message}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
        closeDialog={closeDialog}
      />
    </div>
  );
}
export default Header;
