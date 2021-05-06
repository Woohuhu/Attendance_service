import { AttendanceController } from "./../../controller/attendance.controller";

export class AttendanceService {
  static getAttendanceByDate = async (date) => {
    try {
      const result = await AttendanceController.getAttendanceByDate(date);
      return result.data;
    } catch (error) {
      throw new Error(error);
    }
  };
}
