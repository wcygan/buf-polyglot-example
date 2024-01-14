# buf-polyglot-example

This is an example of how to use [buf](https://buf.build/) in a multi-language repository.

## Generate stubs from protobuf schemas

Run the following command:

```bash
$ buf generate proto
```

## Start the server

Run the following command:

```bash
go run go-server/main.go
```

## Testing with grpcurl

You can use [grpcurl](https://github.com/fullstorydev/grpcurl) to test the server:

#### Request

```bash
grpcurl -plaintext -proto schemas/proto/hello/v1/hello.proto -d '{"name": "YourName"}' localhost:50051 hello.v1.HelloService/SayHello
```

#### Response

```json
{
  "message": "Hello YourName"
}
```

## How did I get the [buf.yaml](proto/buf.yaml) file?

I ran the following command in the [proto](proto) directory:

```bash
$ buf mod init
```

## How did I get the [buf.gen.yaml](buf.gen.yaml) file?

I created it, then I added package names as well as plugins for the languages which I wanted to generate protobuf and
grpc stubs for.

## How did I get IntelliJ to index the Java Subproject?

I open the *root directory* of this project in IntelliJ and use the Go plugin. However, IntelliJ doesn't necessarily
index the subprojects properly.

| Before                                        | During                                 | After                                        |
|-----------------------------------------------|----------------------------------------|----------------------------------------------|
| <img src="resources/before-mark-sources.png"> | <img src="resources/mark-sources.png"> | <img src="resources/after-mark-sources.png"> |

## What are the [proto](proto) and `generated` folders for?

### [proto](proto)

This folder contains the schemas that will be shared between clients and servers.

You define your schemas as [Protocol Buffers](https://protobuf.dev/) and then compile them into code for the languages
you want to use.

[Buf](https://buf.build/) is used to compile the schemas into code. If you don't want to use buf, you can
use [protoc](https://grpc.io/docs/protoc-installation/) directly.

### `generated`

This folder contains the generated code for the languages you want to use. The [buf.gen.yaml](buf.gen.yaml) file
defines which languages you want to generate code for.

#### Java Integration

To integrate the Java code into the [java-client](java-client) project, I specified the [generated/java](generated/java)
as a [SourceSet](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/SourceSet.html)
in [build.gradle](java-client/app/build.gradle).
I also needed to make sure that the [generated](generated) folder was marked as a source folder in IntelliJ for proper
indexing. Lastly, I needed to make sure that the version of protoc-gen-grpc-java:1.61.0 was the same
in [buf.gen.yaml](buf.gen.yaml) and [build.gradle](java-client/app/build.gradle).

#### Go Integration

To integrate the Go code into the [go-server](go-server) project, I create a [go module](go.mod) in the root directory of the project, which
allows me to import the generated code as a dependency in [go-server/main.go](go-server/main.go).