import { Response } from "./response";
export interface Question {
  label: string;
  responses: Response[];
}