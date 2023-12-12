function json_handle(){
    const xhr = new XMLHttpRequest();
    const url = "AdminServlet";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const json = JSON.parse(xhr.responseText);
            alert("Student added successfully!");
        } else if (xhr.readyState === 4 && xhr.status !== 200) {
            alert("Student was not added!");
        }
    };
}