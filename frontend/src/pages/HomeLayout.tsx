import { Outlet, useNavigation } from "react-router-dom";
import Header from "../components/Header";
import Navbar from "../components/Navbar";
import Loading from "../components/Loading";

function HomeLayout() {
  const navigation = useNavigation();
  const isPageLoading = navigation.state === "loading";  

  return (
    <>
      <Header />
      <Navbar />
      <div>
        {isPageLoading?<Loading />:<Outlet />}
      </div>
    </>
  );
}
export default HomeLayout;
