import React from 'react'
import { Field, Form, Formik, ErrorMessage } from 'formik';
import * as Yup from 'yup';

function RequestFormStyled({ submitHandler }) {
  const schema = Yup.object().shape({
    title: Yup.string().required(),
    type: Yup.string(),
    year: Yup.number()
  });

  const initialValues = {
    title: '',
    type: '',
    year: ''
  };

  const typeOptions = ['movie', 'series', 'episode'];

  return (
    <div>
      <Formik
        initialValues={initialValues}
        validationSchema={schema}
        onSubmit={(values) => submitHandler({
          title: values.title,
          type: values.type,
          year: values.year
        })}>
        {(formik) => {
          const { errors, touched, isValid, dirty, values } = formik;
          return (
            <div class='w-50 align-self-center'>
              <Form>
                <div class='row mb-2'>
                  <label for='title' class='col-sm-2 col-form-label'>Full title</label>
                  <div class='col-sm-10'>
                    <Field id='title' type='text' name='title' class='form-control' placeholder='Full title' />
                    <ErrorMessage name='title' component='span' class='ms-2'/>
                  </div>
                </div>
                <div class='row mb-2'>
                  <label for='type' class='col-sm-2 col-form-label'>Type</label>
                  <div class='col-sm-10'>
                    <Field id='type' as='select' name='type' class='form-control'>
                      <option value=''>Any</option>
                      {typeOptions.map((option) => (
                        <option key={option} value={option}>
                          {option[0].toUpperCase() + option.slice(1)}
                        </option>
                      ))}
                    </Field>
                    <ErrorMessage name='type' component='span' class='ms-2'/>
                  </div>
                </div>
                <div class='row mb-2'>
                  <label for='year' class='col-sm-2 col-form-label'>Year (optional)</label>
                  <div class='col-sm-10'>
                    <Field id='year' type='number' name='year' class='form-control' placeholder='Year' />
                    <ErrorMessage name='year' component='span' class='ms-2'/>
                  </div>
                </div>
                <button type='submit' class='btn btn-sm btn-primary' disabled={!(dirty && isValid)}>Submit</button>
              </Form>
            </div>
          );
        }}
      </Formik>
    </div>
  )
}

export default RequestFormStyled