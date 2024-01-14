# buf-polyglot-example

This is an example of how to use [buf](https://buf.build/) in a multi-language repository.

## Generate the code

Enter the [/schemas](schemas) directory and generate the code:

```bash
$ cd schemas
$ buf generate proto
```

## How did I get the [buf.yaml](schemas/proto/buf.yaml) file?

I ran the following command:

```bash
$ buf mod init
```

## How did I get the [buf.gen.yaml](schemas/buf.gen.yaml) file?

I created it, then I added package names as well as plugins for the languages which I wanted to generate protobuf and
grpc stubs for.