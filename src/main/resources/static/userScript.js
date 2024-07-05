async function getUserInfo() {
    let temp = await fetch('http://localhost:8053/user/auth')
    let user = await temp.json()
    let username = user.username
    let roles = user.roles
    getUser(user)
    getNavBar({username, roles})

}
function getNavBar({username, roles}) {
    let rolesNavBar = ''
    roles.forEach(role => {
        rolesNavBar += role.name.replace('ROLE_', '') + " "
    })
    document.getElementById('headerUsername').innerHTML = username
    document.getElementById('headerUserRoles').innerHTML = rolesNavBar
}
function getUser(user) {
    if (user.roles) {
    let rolesUser =''
    user.roles.forEach(role => {
        rolesUser += role.name.replace('ROLE_', '') + " "
    })
    let temp = ''
    temp +=
        `<tr>
          <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.salary}</td>
            <td>${rolesUser}</td>
         </tr>`
    document.getElementById('userInfoId').innerHTML = temp
    }
}
void getUserInfo()