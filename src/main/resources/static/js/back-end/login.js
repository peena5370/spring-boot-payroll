/**
 * @module  loginModule
 * @method  {@link loginValidate}
 */

/**
 * Login validation method
 */
function loginValidate() {
  var data = {
      username: $("input[name='username']").val(),
      password: $("input[name='password']").val()
  };
  if((data.username=="")||(data.password=="") ||(data.username=="" && data.password=="")) {
    alert("Username or password cannot be empty. Please input again.");
  } else {
    $.ajax({
      type: "POST",
      url: $contextPath + "back-end/login/validate",
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(data),
      success: function (resp) {
        switch(resp.code) {
          case 200:
            window.location.href = $contextPath + resp.httpUrl;
            break;
          case 101:
            alert(resp.msg);
            location.reload();
            break;
          default:
            break;
        }
      },
      error: function (err) {
        alert("Wrong username or password. Please relogin.");
        location.reload();
      }
    });
  }
}