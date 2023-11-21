import React, { useEffect, useState } from 'react'
import RequestForm from '../components/RequestForm';

function Home() {
  const [title, setTitle] = useState('');
  const [type, setType] = useState('');
  const [year, setYear] = useState('');

  const [data, setData] = useState(null);

  useEffect(() => {
    async function fetchData() {
      try {
        if (title) {
          const response = await fetch(`${process.env.REACT_APP_API_URL}/?apiKey=${process.env.REACT_APP_API_KEY}${title}${type}${year}&plot=full`);
          setData(await response.json());
        }
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, [title, type, year]);

  function submitHandler(values) {
    if (values.title) {
      setTitle('&t=' + values.title);
    } else {
      setTitle(values.title);
    }

    if (values.type) {
      setType('&type=' + values.type);
    } else {
      setType(values.type);
    }

    if (values.year) {
      setYear('&y=' + values.year);
    } else {
      setYear(values.year);
    }
  }

  return (
    <div>
      <RequestForm submitHandler={submitHandler}/>
      {data?.Error && <div>{data.Error}</div>}
      {data && !data?.Error && (
        <div>
          <h2>{data.Title}</h2>
          <div>Type: {data.Type}</div>
          <div>Genre: {data.Genre}</div>
          <div>Release date: {data.Released}</div>
          <div>Country: {data.Country}</div>
          <div>Running time: {data.Runtime}</div>
          <div>IMDB Rating: {data.imdbRating}</div>
          <div>Directed by: {data.Director}</div>
          <h3>Plot</h3>
          <div>{data.Plot}</div>
        </div>
      )}
    </div>
  )
}

export default Home