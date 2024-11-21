ClassicEditor.create(document.querySelector("#editor")).catch((error) => {
    console.error(error)
})

ClassicEditor.create(document.querySelector("#book-description")).catch((error) => {
    console.error(error)
})

ClassicEditor.create(document.querySelector("#post-content")).then(
    editor => {
        editor.ui.view.editable.element.style.minHeight = '400px';
    }).catch((error) => {
    console.error(error)
})

/*ClassicEditor.create(document.querySelector("#post-content")).catch((error) => {
    console.error(error)
})*/
