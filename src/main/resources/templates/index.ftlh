<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Dictionary</title>
    <#--    <link rel="stylesheet" href="css/style.css">-->
</head>
<body>
<div class="text-center">
    <span>Всего слов: ${count}</span>
    <span>Изучено слов: ${learn}</span>
</div>
<h3 class="text-center">Выберите правильный перевод:</h3>
<a href="/dictionary">Словарь</a>
<p class="text-center">${word.word}</p>
<div class="text-center">
    <#list words as word>
        <form id="form" action="/${word.id}" method="post">
            <button style="width: 300px" id="${word.id}" class="buttons btn btn-primary mb-3">${word.translate}</button>
        </form>
    </#list>
    <form action="/change" id="change-form" method="post">
        <button style="width: 300px" type="submit" class="btn btn-secondary" id="button">Next</button>
    </form>
</div>

<script>
    const buttons = document.querySelectorAll('.buttons');
    const form = document.getElementById("form");

    async function handleClick(e) {
        e.preventDefault()
        const data = new FormData(form)
        const options = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: data
        };
        await fetch("/" + e.target.id, options)
    }

    buttons.forEach(b => {
        b.addEventListener('click', function () {
            if (b.id === `${word.id}`) {
                b.classList.add('buttons', 'btn', 'btn-success')
            } else {
                b.classList.remove('buttons', 'btn', 'btn-success')
                b.classList.add('buttons', 'btn', 'btn-danger')
            }
        })
    })
    buttons.forEach(btn => {
        btn.addEventListener('click', handleClick)
    })

    // const changeForm = document.getElementById('change-form')
    // const changeButton = document.getElementById('button')
    //
    // async function handleClick(e) {
    //     e.preventDefault()
    //     const data = new FormData(changeForm)
    //     const options = {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json'
    //         },
    //         body: data
    //     };
    //     await fetch('/change', options)
    // }
    //
    // changeForm.addEventListener('submit', handleClick)

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>