import axiosAuth from "./../service/global/axios.auth";

export class UserController {
  static createUser = async (req) => {
    try {
      const { id, name, password } = req;
      return await axiosAuth.post("/v1/user", {
        id,
        name,
        password,
      });
    } catch (error) {
      throw new Error(error);
    }
  };

  static refreshToken = async (req) =>{
    try{
      const {id} = req;
      return await axiosAuth.post("/v1/refreshtoken",{
        id
      });
    }catch(error){
      throw new Error(error);
    }
  }
}
