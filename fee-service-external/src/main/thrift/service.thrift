namespace java info.developerblog.services.user

exception TUserNotFoundException {
    1: string message
}

exception TUnauthorizedException {
    1: string message
}

struct TAuthToken {
    1: required string token
}

struct TAuthData {
    1: required i64 userId
}

struct TUser {
    1: required i64 id
    2: optional string lastname
    3: optional string firstname
    4: optional TBalance balance
}

struct TBalance {
    1: required i64 amount
    2: required string currency = "RUR"
}

service TUserService {
    TUser getUserById(1: required TAuthToken authData) throws (1: TUserNotFoundException e, 99: TUnauthorizedException ue)
}