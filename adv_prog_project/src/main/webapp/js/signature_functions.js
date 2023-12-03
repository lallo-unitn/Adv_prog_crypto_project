function init() {
    document.getElementById('inputfile')
        .addEventListener('change', function () {

            let fr = new FileReader();
            fr.onload = function () {
                document.getElementById('output')
                    .textContent = fr.result;
                localStorage.setItem('privKey', fr.result);
                document.getElementById('outputKey')
                    .textContent = localStorage.getItem('privKey');
            }

            fr.readAsText(this.files[0]);
        })
}