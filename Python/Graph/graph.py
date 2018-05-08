from collections import deque


class Vertex():
    def __init__(self,value, connect_list ):
        self.__value = value
        self.__connect_list = set( connect_list )
        self.__status = 0
    
    def get_value(self):
        return self.__value

    def get_adj_list(self):
        return self.__connect_list

    def add_adj_element(self,vertex_value, weight ):
        self.__connect_list.add( (vertex_value,weight) )
    
    def is_adjacent(self,vertex_value):
        adj_values = [ v[0] for v in self.__connect_list ]
        return vertex_value in adj_values

    def get_status(self):
        return self.__status
    
    def set_status(self,status):
        self.__status = status

class Graph():
    
     def __init__( self ):
        self.__adj_list = {}
        self.__dfs_result_tree = []

     def __check_existence_of_vertex(self, vertex_list ):
        if( len(vertex_list) == 0 ):
            return True
        return reduce( lambda x,y: (x and y), [ v[0] in self.__adj_list.keys() for v in vertex_list ] )

     def insert_vertex(self, vertex_value, adj_list ):
        if self.__check_existence_of_vertex(adj_list):
            self.__adj_list[vertex_value] = Vertex( vertex_value, adj_list )
            for v in adj_list:
                self.__adj_list[ v[0] ].add_adj_element( vertex_value, v[1] )
        else:
            raise Exception("Invalid vertex list")

     def bfs( self, start_vertex ):
        if start_vertex not in self.__adj_list.keys():
            return []
        result_tree = [ (start_vertex, "-",0) ]
        vertex_queue = deque( [ start_vertex ] )
        self.__adj_list[start_vertex].set_status(1)
        count = 1
        while len(vertex_queue) != 0:
            curr_vertex = vertex_queue.popleft()
            for v in list(self.__adj_list[curr_vertex].get_adj_list()):
                if(self.__adj_list[v[0]].get_status() == 0):
                    vertex_queue.append(v[0])
                    self.__adj_list[v[0]].set_status(1)
                    result_tree.append( (v[0], count, curr_vertex) )
            count += 1
        return result_tree
     
     def dfs(self, start_vertex ):
        self.__adj_list[start_vertex].set_status(1)
        self.__recursive_dfs(start_vertex,0,"-")
        return self.__dfs_result_tree

     def __recursive_dfs(self, start_vertex, count, father ):
        self.__dfs_result_tree.append( ( start_vertex, father, count) )
        if start_vertex not in self.__adj_list.keys():
            return None
        vertex_stack = [ v[0] for v in self.__adj_list[start_vertex].get_adj_list() if self.__adj_list[v[0]].get_status() == 0]
        while( len(vertex_stack) != 0 ):
            adj_vertex = vertex_stack.pop()
            self.__adj_list[adj_vertex].set_status(1)
            self.__recursive_dfs(adj_vertex, count +1, start_vertex)

    

     def show_graph(self):
        for k in self.__adj_list.keys():
            print str( self.__adj_list[k].get_value() ) + " " + str( list( self.__adj_list[k].get_adj_list()) )


if __name__ == "__main__":
    graph  = Graph()
    graph.insert_vertex(1,[])
    graph.insert_vertex(2,[(1,1)])
    graph.insert_vertex(5,[(1,1),(2,1)])
    graph.insert_vertex(3,[(5,1)])
    graph.insert_vertex(4,[(5,1)])
    print graph.dfs(1)
    #print graph.bfs(1)
    graph.show_graph()
