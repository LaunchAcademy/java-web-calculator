<%
  Integer previousSum = (Integer)request.getAttribute("previousSum");
%>
<h1>Perform a calculation</h1>

<% if(previousSum != null) { %>
<p>Your previous sum calculation was: <%= previousSum %></p>
<% } %>

<form action="/sum-calculations" method="POST">
  <div>
    <label for="firstNumber">First Number</label>
    <input type="number" id="firstNumber" name="firstNumber" value="" required="required" />
  </div>
  <div>
    <label for="lastNumber">Second Number</label>
    <input type="number" id="lastNumber" name="secondNumber" value="" required="required" />
  </div>

  <div>
    <input type="checkbox" name="sumWithPrevious" id="sumWithPrevious" value="1" />
    <label for="sumWithPrevious">Add to previous calculation</label>
  </div>

  <div>
    <input type="submit" value="Add" />
  </div>
</form>