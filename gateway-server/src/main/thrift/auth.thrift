namespace java info.developerblog.services.user

exception TUnauthorizedException {
    1: string message
}

struct TAuthToken {
    1: required string token
}

struct TAuthData {
    1: required i64 userId
}