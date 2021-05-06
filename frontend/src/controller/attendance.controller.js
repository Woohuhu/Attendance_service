import axiosResource from "./../service/global/axios.resource";
export class AttendanceController {
  static getAttendanceByDate = async (date) => {
    try {
      return await axiosResource.get(`/v1/attendacnce/date/${date}`);
    } catch (error) {
      throw new Error(error);
    }
  };
}
