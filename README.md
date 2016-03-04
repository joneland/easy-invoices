Automating the dull process of generating timesheets and invoices every month.

## Usage
`lein run sample.details`

> Note that wkhtmltopdf must be available on the command line. It can be downloaded at http://wkhtmltopdf.org

## File contents

The sample details file follows a strict layout as described below.

| Line | Contents | Example |
|---|---|---|
| 1 | Name | Jon Eland |
| 2 | Client | Foobar LTD |
| 3 | Days Worked | 1 0 0 1 1 - - 1 1 1 0 1 |
| 4 | Month | March |
