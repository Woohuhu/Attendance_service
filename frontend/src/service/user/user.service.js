import { UserController } from "./../../controller/user.controller";

export class UserService {
  static createUser = async (req) => {
    try {
      const result = await UserController.createUser(req);
      return result.data;
    } catch (error) {
      throw new Error(error);
    }
  };

  static refreshToken = async (req)=>{
    try{
      const result = await UserController.refreshToken(req);
      return result.data;
    }catch(error){
      throw new Error(error);
    }
  }
}
