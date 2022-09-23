<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <title>Dictionary</title>
        <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<form action="/upload" class="row g-3" id="place" method="post" enctype="multipart/form-data">
    <div class="input__wrapper mt-5">
        <input hidden name="file" type="file" id="input__file" class="text-center input input__file">
        <label for="input__file" class="input__file-button">
            <span class="input__file-icon-wrapper"><i class="bi bi-download"></i></span>
            <button style="width: 300px" type="submit" class="btn btn-primary add" id="button">Добавить</button>
        </label>
    </div>
</form>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Слово</th>
        <th scope="col">Перевод</th>
        <th scope="col">Рейтинг</th>
    </tr>
    </thead>
    <tbody>
    <#list all as a>
        <tr>
            <td>${a.word}</td>
            <td>${a.translate}</td>
            <td>${a.rating}</td>
        </tr>
    </#list>
    </tbody>
</table>




<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>