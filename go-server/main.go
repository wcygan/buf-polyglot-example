package main

import (
	"context"
	pb "github.com/wcygan/buf-polyglot-example/generated/go/hello/v1"
	"google.golang.org/grpc"
	"log"
	"net"
)

type Server struct {
	pb.UnimplementedHelloServiceServer
}

func NewServer() *Server {
	return &Server{}
}

func (s *Server) SayHello(_ context.Context, in *pb.SayHelloRequest) (*pb.SayHelloResponse, error) {
	log.Printf("Received: %v", in.GetName())
	return &pb.SayHelloResponse{Message: "Hello " + in.GetName()}, nil
}

const (
	port = ":50051"
)

func main() {
	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	s := grpc.NewServer()
	pb.RegisterHelloServiceServer(s, NewServer())
	log.Printf("server listening at %v", lis.Addr())
	if err := s.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
