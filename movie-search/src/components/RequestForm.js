import React from 'react'
import { Field, Form, Formik, ErrorMessage } from 'formik';
import * as Yup from 'yup';

function RequestForm({ submitHandler }) {
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
            <div>
              <Form>
                <div>
                  <label for='title'>Full title</label>
                  <div>
                    <Field id='title' type='text' name='title'placeholder='Full title' />
                    <ErrorMessage name='title' component='span'/>
                  </div>
                </div>
                <div>
                  <label for='type'>Type</label>
                  <div>
                    <Field id='type' as='select' name='type'>
                      <option value=''>Any</option>
                      {typeOptions.map((option) => (
                        <option key={option} value={option}>
                          {option[0].toUpperCase() + option.slice(1)}
                        </option>
                      ))}
                    </Field>
                    <ErrorMessage name='type' component='span'/>
                  </div>
                </div>
                <div>
                  <label for='year'>Year (optional)</label>
                  <div>
                    <Field id='year' type='number' name='year' placeholder='Year' />
                    <ErrorMessage name='year' component='span'/>
                  </div>
                </div>
                <button type='submit' disabled={!(dirty && isValid)}>Submit</button>
              </Form>
            </div>
          );
        }}
      </Formik>
    </div>
  )
}

export default RequestForm