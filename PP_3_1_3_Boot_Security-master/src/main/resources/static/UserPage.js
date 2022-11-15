$(async function() {
    await thisUser();
});
async function thisUser() {
    fetch("http://localhost:8080/admin/user")
        .then(res => res.json())
        .then(data => {
            $('#headerUsername').append('<span>You logged as: </span> ' + data.username);
            let roles = data.roles.map(role => " " + role.name.replaceAll("ROLE_", ''));
            $('#headerRoles').append('<span>with role: </span> ' + roles);

            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.name}</td>
                <td>${data.lastname}</td>
                <td>${data.age}</td>
                <td>${roles}</td>)`;
            $('#userPanelBody').append(user);
        })
}
