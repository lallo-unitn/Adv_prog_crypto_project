function json_handle(){
    const xhr = new XMLHttpRequest();
    const url = "AddStudentServlet";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const json = JSON.parse(xhr.responseText);
            alert(json.message);
        } else if (xhr.readyState === 4 && xhr.status !== 200) {
            alert("Student was not added!");
        }
    };
}