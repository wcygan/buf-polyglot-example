use schemas::hello::v1::hello_service_client::HelloServiceClient;
use schemas::hello::v1::SayHelloRequest;

#[tokio::main]
async fn main() {
    let req = SayHelloRequest {
        name: "World".to_string(),
    };

    let mut client = HelloServiceClient::connect("http://localhost:50051").await.unwrap();

    let resp = client.say_hello(req).await.unwrap();

    println!("{}", resp.get_ref().message);
}
