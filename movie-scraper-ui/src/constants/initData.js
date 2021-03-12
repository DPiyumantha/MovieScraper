export const newUser = {
    username: "",
    password: "",
    email: "",
    enabled: 1,
    accountNonExpired: 1,
    credentialsNonExpired: 1,
    accountNonLocked: 1,
    roles: [
      {
        id: 2,
        name: "ROLE_operator",
        permissions: [
          {
            id: 1,
            name: "create_profile",
          },
          {
            id: 3,
            name: "update_profile",
          },
          {
            id: 2,
            name: "read_profile",
          },
        ],
      },
    ],
  }