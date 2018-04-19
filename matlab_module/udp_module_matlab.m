% Create UDP object 'udpConn' 
udpConn = udp('192.168.0.103', 'RemotePort', 4445, 'LocalPort', 8845); 

% Set size of receiving buffer, if needed. 
% set(u, 'InputBufferSize', 30000);

% Open connection to the server. 
fopen(udpConn);
disp('start conn');
i = 10;

try

    while(i>0)
        % Send data to the GW 
        fprintf(udpConn, 'M'); 

        % Pause for the communication delay 
        pause(0.5) 

        % Receive data from GW 
        while (get(udpConn, 'BytesAvailable') > 0) 
        % udpConn.BytesAvailable 
        disp('Data from GW: ')
        disp(fscanf(udpConn))
        end 
        i = i-1;
    
    end
    
catch
    warning('Problem with conn.');
    closeConn(udpConn);

end

% Disconnect and clean up the server connection.
closeConn(udpConn)
disp('end conn')

function closeConn(u)
    fclose(u); 
    delete(u); 
    clear u
end