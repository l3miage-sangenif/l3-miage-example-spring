import { Question } from "./question";

export interface Miahoot {
  miahootId:number;
  nom: string;
  questions: Question[];
}
