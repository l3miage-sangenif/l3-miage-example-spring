import { Question } from "./question";

export interface Miahoot {
  uid:String;
  miahootId:number;
  nom: string;
  questions: Question[];
}
