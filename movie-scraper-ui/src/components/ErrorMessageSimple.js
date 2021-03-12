import '../styles.css'

export default function ErrorMessageSimple({errorMsg, reset}){
return (

    <div className={"errorMsg"}>
        <h2>Oops! Something went wrong</h2>
        <p>{errorMsg}</p>
        <button onClick={reset}>OK</button>
    </div>
)
}