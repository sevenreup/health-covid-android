export interface Advice {
    readonly id: number
    readonly shortTitle: string
    readonly title: string
    readonly advice: string
    readonly video: string
    readonly image: number
    readonly hasVideo: boolean
    readonly expanded: boolean
    readonly languageOwnerId: number
}