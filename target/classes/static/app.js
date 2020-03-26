$(document).ready(function () {
  $.get("countries/getAll", function (data) {
    $('#mainBody').html('');
    $('#confirmed').html('');
    $('#recovered').html('');
    $('#deaths').html('');
    let index = 0;
    $.map(data, (val) => {
      let tdc = '<td>' + val.confirmed + '</td>'
      let tdr = '<td>' + val.recovered +  '</td>'
      let tdd = '<td>' + val.deaths + '</td>'
      let th = '<th scope="row">' + val.country + '</th>'
      let tr = '<tr id="' + 'id' + index + '">' + th + tdd + tdc + tdr + '</tr>'
      $('#mainBody').append(tr);
      $('#id' + index).click(function (val) {
        let cnt = String(val.currentTarget.cells[0].innerText);
        $('#country').html(cnt);
        $('#confirmed').html(val.currentTarget.cells[1].innerText);
        $('#recovered').html(val.currentTarget.cells[2].innerText);
        $('#deaths').html(val.currentTarget.cells[3].innerText);
        $.get("countries/" + cnt, function (cities) {
          $('#cityBody').html('');
          $.map(cities, (val) => {
            let tdc2 = '<td>' + val.confirmed + '</td>'
            let tdr2 = '<td>' + val.recovered + '</td>'
            let tdd2 = '<td>' + val.deaths + '</td>'
            let th2 = '<th scope="row">' + val.country + '</th>'
            let tr2 = '<tr id="' + 'id' + index + '">' + th2 + tdd2 + tdc2 + tdr2 + '</tr>'
            $('#cityBody').append(tr2);
          });
        });
      });
      index += 1;
    })
  });

});