import { Option } from './option'

export interface QuestionDetails {
    questionType?: string
    questionText: string
    options?: Option[]
}