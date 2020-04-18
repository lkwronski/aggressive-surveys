export interface Question {
    questionId: number,
    pollId: number,
    questionType: string,
    questionText: string,
    answer?: string,
    options?: any,
}