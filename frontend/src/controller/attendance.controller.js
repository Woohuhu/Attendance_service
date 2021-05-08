import axiosResource from "./../service/global/axios.resource";
export class AttendanceController {
  static getAttendanceByDate = async (date) => {
    try {
      const result = await axiosResource.get(`/v1/attendance/date/${date}`);
      return result.data;
    } catch (error) {
      this.$log.debug(error);
      throw new Error(error);
    }
  };

  static getAttendanceById = async (id) => {
    try {
      const result = await axiosResource.get(`/v1/attendance/${id}`);
      return result.data;
    } catch (error) {
      this.$log.debug(error);
      throw new Error(error);
    }
  };
}
