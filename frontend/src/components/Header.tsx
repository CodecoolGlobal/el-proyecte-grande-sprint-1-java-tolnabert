import { NavLink } from "react-router-dom";
import "../css/header.css";
import Logo from "./Logo";

function Header() {
  const isLoggedIn = localStorage.getItem("jwtToken") !== null;
  const roles = localStorage.getItem("roles");

  const handleLogout = () => {
    localStorage.removeItem("jwtToken");
    localStorage.removeItem("roles");
  };

  return (
    <div className='header'>
      <Logo />
      {isLoggedIn ? (
        <>
          <NavLink className='navlink-button' to={"/"} onClick={handleLogout}>
            Logout
          </NavLink>
        </>
      ) : (
        <>
          <NavLink className='navlink-button' to={"/register"}>
            Register
          </NavLink>
          <NavLink className='navlink-button' to={"/login"}>
            Login
          </NavLink>
        </>
      )}
    </div>
  );
}
export default Header;
